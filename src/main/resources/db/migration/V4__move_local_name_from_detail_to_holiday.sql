DELETE FROM holiday_detail WHERE type = 'LOCAL_NAME';

ALTER TABLE holiday ADD COLUMN local_name VARCHAR(255);
