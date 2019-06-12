ALTER TABLE `apikey` ADD COLUMN `enabled` BOOLEAN NOT NULL DEFAULT TRUE;
UPDATE `apikey` SET `enabled` = true;