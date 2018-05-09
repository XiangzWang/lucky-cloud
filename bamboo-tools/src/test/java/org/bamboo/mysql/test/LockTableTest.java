package org.bamboo.mysql.test;

import org.bamboo.datasource.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LockTableTest {
    public static void main(String[] args) throws SQLException {
//        batchUpdate2();

        ExecutorService pool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
//                        batchUpdate1();
                        batchUpdate2();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            };

            pool.execute(t);
        }

        pool.shutdown();
    }

    public static void batchUpdate1() throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            long start = System.currentTimeMillis();
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement("update o_org_test set org_name = ?, org_type = ? where org_no = ?");
            pst.setString(1, "贵州");
            pst.setString(2, "02");
            pst.setString(3, "13104");
            pst.addBatch();
            pst.setString(1, "泰兴");
            pst.setString(2, "04");
            pst.setString(3, "13104");
            pst.addBatch();
            pst.setString(1, "江苏");
            pst.setString(2, "03");
            pst.setString(3, "13104");
            pst.addBatch();

            int[] res = pst.executeBatch();

//            Random r = new Random();
//            TimeUnit.MILLISECONDS.sleep(r.nextInt(100));
            con.commit();

            long end = System.currentTimeMillis();
            System.out.println("1入库成功,耗时:" + (end - start) + "ms");
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    /**
     * Oracle merge into
     * @throws SQLException
     */
    public static void batchUpdate2() throws SQLException {
        Connection con = null;
        PreparedStatement pst = null;
        try {
            long start = System.currentTimeMillis();
            con = ConnectionUtils.getConnection();

//            pst = con.prepareStatement("delete from o_org_test where org_no = ?");
//            pst.setString(1, "13105");

//            pst = con.prepareStatement("insert into o_org (org_name, org_no) values (?, ?)");
//            pst.setString(1, "泰兴");
//            pst.setString(2, "13105");
//            pst.executeUpdate();

            //多线程同时insert新值会报违反唯一约束
            String sql = "MERGE INTO O_ORG_TEST A USING (SELECT ? ORG_NO, ? ORG_NAME, ? ORG_TYPE from DUAL) B ON (A.ORG_NO = B.ORG_NO AND A.ORG_TYPE = B.ORG_TYPE)  \n" +
                    "WHEN MATCHED THEN  \n" +
                    "  UPDATE SET A.ORG_NAME = B.ORG_NAME \n" +
                    "WHEN NOT MATCHED THEN  \n" +
                    "  INSERT(A.ORG_NO, A.ORG_NAME, A.ORG_TYPE) VALUES(B.ORG_NO, B.ORG_NAME, B.ORG_TYPE)";
            pst = con.prepareStatement(sql);
            pst.setString(1, "12344");
            pst.setString(2, "测试");
            pst.setString(3, "02");
            pst.executeUpdate();

            pst.setString(1, "12344");
            pst.setString(2, "测试");
            pst.setString(3, "03");
            pst.executeUpdate();

            pst.setString(1, "12344");
            pst.setString(2, "测试");
            pst.setString(3, "03");
            pst.executeUpdate();

            pst.setString(1, "12344");
            pst.setString(2, "测试");
            pst.setString(3, "02");
            pst.executeUpdate();

            pst.setString(1, "12344");
            pst.setString(2, "测试");
            pst.setString(3, "02");
            pst.executeUpdate();

//            pst.executeBatch();
            con.commit();

            long end = System.currentTimeMillis();
            System.out.println("2入库成功,耗时:" + (end - start) + "ms");
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
