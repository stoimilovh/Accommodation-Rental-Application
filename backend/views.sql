CREATE MATERIALIZED VIEW accommodation_counts_by_host AS
SELECT host_id, COUNT(*) AS accommodation_count
FROM accommodation
GROUP BY host_id, name;
