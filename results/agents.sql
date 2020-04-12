-- Expected Value of userid 737 for status : 1
-- Actual Value of userid 737 for status : 0
--
-- Expected Value of userid 759 for status : 1
-- Actual Value of userid 759 for status : 0
--
-- Expected Value of userid 841 for status : 1
-- Actual Value of userid 841 for status : 0
--
-- Expected Value of userid 885 for status : 1
-- Actual Value of userid 885 for status : 0
--
-- Expected Value of userid 893 for status : 1
-- Actual Value of userid 893 for status : 0
--
-- Preparing to Sync...
UPDATE agents SET status=1 WHERE userid=737;
UPDATE agents SET status=1 WHERE userid=759;
UPDATE agents SET status=1 WHERE userid=841;
UPDATE agents SET status=1 WHERE userid=885;
UPDATE agents SET status=1 WHERE userid=893;