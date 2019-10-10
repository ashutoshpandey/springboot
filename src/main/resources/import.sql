CREATE TABLE authorities (
  username VARCHAR(256) NOT NULL,
  authority VARCHAR(256) NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);