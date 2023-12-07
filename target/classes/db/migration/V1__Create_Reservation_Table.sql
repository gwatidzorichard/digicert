CREATE TABLE IF NOT EXISTS reservation (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    guest_name VARCHAR(255) NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    agent_name VARCHAR(255)
);