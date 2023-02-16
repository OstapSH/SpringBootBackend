USE trip;

DELIMITER //
SET GLOBAL log_bin_trust_function_creators = 1;
DROP FUNCTION IF EXISTS getMinPlacePrice;
DELIMITER //
CREATE FUNCTION getMinPlacePrice()
    RETURNS INT
BEGIN
    RETURN (SELECT min(price) FROM place);
END //

DROP PROCEDURE IF EXISTS insertIntoCountry;
CREATE PROCEDURE insertIntoCountry(
    IN new_country_name VARCHAR(30)
)
BEGIN
    INSERT INTO country(name)
        VALUE (new_country_name);
END //

DROP PROCEDURE IF EXISTS addUserPlace;
DELIMITER //
CREATE PROCEDURE addUserPlace(
    IN user_id1 INTEGER,
    IN place_id1 INTEGER
)
BEGIN
    INSERT INTO user_place VALUES (user_id1, place_id1);
END //


DROP PROCEDURE IF EXISTS insertCountries //
CREATE PROCEDURE insertCountries(
    IN countryName VARCHAR(30)
)
BEGIN
    DECLARE i INT;
    SET i = 0;
    label: LOOP
        IF i > 9 THEN LEAVE label;
        END IF;
        INSERT INTO country(name) VALUE (CONCAT(countryName, i));
        SET i = i + 1;
    END LOOP;
END //

DROP PROCEDURE IF EXISTS getMinPrice;
CREATE PROCEDURE getMinPrice(
    OUT min_price INT
)
BEGIN
    SET min_price = getMinPlacePrice();
END //

DROP PROCEDURE IF EXISTS createTables;
CREATE PROCEDURE createTables()
BEGIN
    DECLARE done BOOLEAN DEFAULT false;
    DECLARE username VARCHAR(15);
    DECLARE col_number int DEFAULT 1;
    DECLARE i INT DEFAULT 1;
    DECLARE query VARCHAR(200) DEFAULT '';
    DECLARE user_cursor CURSOR FOR
        SELECT name FROM user;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = true;
    OPEN user_cursor;
    loop_label: LOOP
        FETCH user_cursor INTO username;
        IF (done = true) THEN LEAVE loop_label;
        END IF;
        SET col_number = FLOOR(RAND() * 10 + 1);
        SET i = 1;
        SET query = '';
        inner_loop: LOOP
            IF (i > col_number) THEN LEAVE inner_loop;
            END IF;
            IF (i = col_number) THEN SET query = CONCAT(query, 'Col', i, ' int');
            ELSE SET query = CONCAT(query, 'Col', i, ' int', ', ');
            END IF;
            SET i = i + 1;
        END LOOP;
        SET @temp_query = CONCAT('CREATE TABLE IF NOT EXISTS `', username, DATE_FORMAT(NOW(), '_%Y_%m_%d_%H_%i_%s'), '` (', query, ');');
        PREPARE my_query FROM @temp_query;
        EXECUTE my_query;
        DEALLOCATE PREPARE my_query;
    END LOOP;
    CLOSE user_cursor;
END //

DELIMITER ;

