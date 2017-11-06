-- 创建存储过程(查询得到男性或女性的数量, 如果传入的是0就女性否则是男性)
DELIMITER $
CREATE PROCEDURE sea.get_user_count(IN sex_id INT, OUT user_count INT)
BEGIN  
IF sex_id=0 THEN
SELECT COUNT(*) FROM sea.p_user WHERE p_user.sex='女' INTO user_count;
ELSE
SELECT COUNT(*) FROM sea.p_user WHERE p_user.sex='男' INTO user_count;
END IF;
END 
$

-- 调用存储过程
DELIMITER ;
SET @user_count = 0;
CALL sea.get_user_count(1, @user_count);
SELECT @user_count;