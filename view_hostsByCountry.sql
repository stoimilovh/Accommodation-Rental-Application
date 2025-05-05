CREATE MATERIALIZED VIEW hosts_by_country AS
SELECT country_id, COUNT(*) AS host_count
FROM host
GROUP BY country_id;
