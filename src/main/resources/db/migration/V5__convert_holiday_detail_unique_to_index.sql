ALTER TABLE holiday_detail DROP CONSTRAINT IF EXISTS uk_holiday_detail_holiday_id_type_source_type;

-- 인덱스 생성
CREATE INDEX idx_holiday_detail_holiday_type_source
    ON holiday_detail (holiday_id, type, source_type);