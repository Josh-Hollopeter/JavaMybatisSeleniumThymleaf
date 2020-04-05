CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment,
  username VARCHAR(20) unique,
  salt TEXT,
  password TEXT,
  firstname VARCHAR(200),
  lastname VARCHAR(200),
  role VARCHAR(20),
  enabled tinyint(4) 
  
);

CREATE TABLE IF NOT EXISTS NOTES (
    noteid INT PRIMARY KEY auto_increment,
    notetitle VARCHAR(20),
    notedescription VARCHAR (1000),
    userid INT,
    foreign key (userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS FILES (
    fileId INT PRIMARY KEY auto_increment,
    filename TEXT,
    contenttype TEXT,
    filesize TEXT,
    userid INT,
    filedata BLOB,
    foreign key (userid) references USERS(userid),
    UNIQUE(filename,userid)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
    credentialid INT PRIMARY KEY auto_increment,
    url VARCHAR(100),
    username VARCHAR (30),
    skeleton TEXT,
    password TEXT,
    userid INT,
    foreign key (userid) references USERS(userid)
);
