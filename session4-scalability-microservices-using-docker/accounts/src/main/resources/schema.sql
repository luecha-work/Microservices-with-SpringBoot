CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `mobile_number` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` VARCHAR(255),
  `updated_by` VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS `accounts` (
  `account_number` BIGINT PRIMARY KEY,
  `customer_id` BIGINT,
  `account_type` VARCHAR(255) NOT NULL,
  `branch_address` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` VARCHAR(255),
  `updated_by` VARCHAR(255),
  FOREIGN KEY (`customer_id`) REFERENCES `customer`(`customer_id`)
);