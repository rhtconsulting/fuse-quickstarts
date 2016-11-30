DROP ALL OBJECTS;
CREATE TABLE my_table (
  id bigint(20) NOT NULL auto_increment,
  key varchar(255) NOT NULL default '',
  value varchar(255) NOT NULL default '',
  PRIMARY KEY  (id)
);