use board_adv;

CREATE TABLE IF NOT EXISTS `board_adv`.`favorite` (
  `fav_id` INT NOT NULL AUTO_INCREMENT,
  `content_id` INT NOT NULL,
  `fav_title` VARCHAR(45) NULL DEFAULT NULL,
  `fav_img_url` VARCHAR(200) NULL DEFAULT NULL,
  `fav_addr` VARCHAR(45) NULL DEFAULT NULL,
  `fav_tel` VARCHAR(45) NULL DEFAULT NULL,
  `user_seq` INT NULL DEFAULT NULL,
  PRIMARY KEY (`fav_id`),
  INDEX `favorite_users_user_seq_fk_idx` (`user_seq` ASC) VISIBLE,
  CONSTRAINT `favorite_users_user_seq_fk`
    FOREIGN KEY (`user_seq`)
    REFERENCES `board_adv`.`users` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
