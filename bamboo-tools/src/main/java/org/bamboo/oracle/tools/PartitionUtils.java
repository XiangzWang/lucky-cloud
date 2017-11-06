package org.bamboo.oracle.tools;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import org.bamboo.datasource.ConnectionUtils;
import org.bamboo.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PartitionUtils {
	static final Logger logger = LoggerFactory.getLogger(PartitionUtils.class);
	
	private static String getAddPartitionSql(String tablespace, String tableName, String dateStr, String[] areaCodeArray) throws ParseException {
		String offsetDateStr = DateUtils.addDay(dateStr, 1);
		StringBuffer sb = new StringBuffer();
		sb.append("alter table ").append(tableName).append("\n");
		sb.append("  add partition PART_").append(dateStr);
		sb.append(" values less than (TO_DATE('").append(offsetDateStr);
		sb.append(" 00:00:00', 'YYYYMMDD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')) \n");
		sb.append("  tablespace ").append(tablespace).append("\n"); // SEA
		sb.append("  pctfree 10 \n");
		sb.append("  initrans 2 \n");
		sb.append("  maxtrans 255 \n");

//		sb.append("    subpartition PART_20171101_ORG01 values ('01') tablespace SEA_E01, \n");
//		sb.append("    subpartition PART_20171101_ORG02 values ('02') tablespace SEA_E02, \n");
//		sb.append("    subpartition PART_20171101_ORG03 values ('03') tablespace SEA_E03, \n");
//		sb.append("    subpartition PART_20171101_ORG04 values ('04') tablespace SEA_E04, \n");
//		sb.append("    subpartition PART_20171101_ORG05 values ('05') tablespace SEA_E05 \n");
		
		if (areaCodeArray != null && areaCodeArray.length > 0) {
			sb.append("  ( \n");
			String areaCode = "";
			for (int i = 0; i < areaCodeArray.length - 1; i++) {
				areaCode = areaCodeArray[i];
				sb.append("    subpartition PART_").append(dateStr).append("_ORG").append(areaCode);
				sb.append(" values ('").append(areaCode).append("') tablespace ");
				sb.append(tablespace).append("_E").append(areaCode).append(", \n");
			}
			areaCode = areaCodeArray[areaCodeArray.length - 1];
			sb.append("    subpartition PART_").append(dateStr).append("_ORG").append(areaCode);
			sb.append(" values ('").append(areaCode).append("') tablespace ");
			sb.append(tablespace).append("_E").append(areaCode).append(" \n");

			sb.append("  ) \n");
		}
		return sb.toString();
	}

	public static void addPartition(String tablespace, String tableName, String fromDateStr, int days, String[] areaCodeArray) throws ParseException, SQLException {
		if (days < 1) {
			days = 1;
		}
		Statement st = null;
		Connection conn = null;
		
		try {
			conn = ConnectionUtils.getConnection();
			st = conn.createStatement();
			for (int i = 0; i < days; i++) {				
				String dateStr = DateUtils.addDay(fromDateStr, i);
				String sql = PartitionUtils.getAddPartitionSql(tablespace, tableName, dateStr, areaCodeArray);			
				st.addBatch(sql);
			}
			if (st != null) {
				st.executeBatch();
				conn.commit();
			}
		} catch (SQLException e) {
			conn.rollback();
			logger.error("扩分区执行错误", e);
		} finally {
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
}
