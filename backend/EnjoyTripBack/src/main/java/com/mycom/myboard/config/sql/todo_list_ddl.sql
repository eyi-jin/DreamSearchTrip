use board_adv;

CREATE TABLE IF NOT EXISTS `board_adv`.`todo_list` (
  `todo_id` INT NOT NULL AUTO_INCREMENT,
  `todo_content` VARCHAR(45) NULL DEFAULT NULL,
  `todo_status` VARCHAR(200) NULL DEFAULT NULL,
  `user_seq` INT NULL DEFAULT NULL,
  PRIMARY KEY (`todo_id`),
  INDEX `todo_users_user_seq_fk_idx` (`user_seq` ASC) VISIBLE,
  CONSTRAINT `todo_users_user_seq_fk`
    FOREIGN KEY (`user_seq`)
    REFERENCES `board_adv`.`users` (`user_seq`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
