USE trip;

DELIMITER //
DROP TRIGGER IF EXISTS onUpdateCard//
CREATE TRIGGER onUpdateCard
    BEFORE UPDATE
    ON credit_card FOR EACH ROW
BEGIN
    DECLARE num int;
    SELECT COUNT(*) INTO num FROM user WHERE id = new.user_id;
    IF num = 0 THEN SIGNAl SQLSTATE '45000'
        SET MESSAGE_TEXT = 'You cannot reference not existing user';
    END IF;
END //

DELIMITER //
DROP TRIGGER IF EXISTS onInsertCard//
CREATE TRIGGER onInsertCard
    BEFORE INSERT
    ON credit_card FOR EACH ROW
BEGIN
    DECLARE num int;
    SELECT COUNT(*) INTO num FROM user WHERE id = new.user_id;
    IF num = 0 THEN SIGNAl SQLSTATE '45000'
        SET MESSAGE_TEXT = 'You cannot reference not existing user';
    END IF;
END //

DROP TRIGGER IF EXISTS onDeleteUser//
CREATE TRIGGER onDeleteUser
    BEFORE DELETE
    ON user FOR EACH ROW
BEGIN
    DECLARE num int;
    SELECT COUNT(*) INTO num FROM credit_card WHERE user_id = old.id;
    IF num = 0 THEN SIGNAl SQLSTATE '45000'
        SET MESSAGE_TEXT = 'You cannot reference not existing user';
    END IF;
END //

DROP TRIGGER IF EXISTS onUpdateUser//
CREATE TRIGGER onUpdateUser
    BEFORE UPDATE
    ON user FOR EACH ROW
BEGIN
    DECLARE num int;
    SELECT COUNT(*) INTO num FROM credit_card WHERE user_id = new.id;
    IF num = 0 THEN SIGNAl SQLSTATE '45000'
        SET MESSAGE_TEXT = 'You cannot reference not existing user';
    END IF;
END //

DROP TRIGGER IF EXISTS onDeletePlace//
CREATE TRIGGER onDeletePlace
    BEFORE DELETE
    ON place FOR EACH ROW
BEGIN
    SIGNAl SQLSTATE '45000'
    SET MESSAGE_TEXT = 'You cannot delete places';
END //

DROP TRIGGER IF EXISTS onDeletePlace//
CREATE TRIGGER onDeletePlace
    BEFORE DELETE
    ON place FOR EACH ROW
BEGIN
    SIGNAl SQLSTATE '45000'
        SET MESSAGE_TEXT = 'You cannot delete places';
END //

DROP TRIGGER IF EXISTS onDeleteUserWithName//
CREATE TRIGGER onDeleteUserWithName
    BEFORE DELETE
    ON user FOR EACH ROW
BEGIN
    IF (old.name = 'Astab8')
        THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Dont you dare!';
    END IF;
END //

DROP TRIGGER IF EXISTS afterDeleteUser//
CREATE TRIGGER afterDeleteUser
    AFTER DELETE
    ON user FOR EACH ROW
BEGIN
    IF (SELECT COUNT(*) FROM user) < 1
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'You cant delete all the users!';
    END IF;
END //

DELIMITER ;



