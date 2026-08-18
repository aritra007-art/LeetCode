DELETE FROM Person p2
USING Person p1
WHERE p2.email = p1.email 
  AND p2.id > p1.id;
