ALTER TABLE `apikey` ADD COLUMN `last_used` datetime;
ALTER TABLE `apikey` ADD COLUMN `last_remote_addr` varchar(45);