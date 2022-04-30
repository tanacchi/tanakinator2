-- Choice table
CREATE TABLE choice (
    choice_id INT PRIMARY KEY,
    choice_name VARCHAR(20),
    choice_value FLOAT
);

INSERT INTO choice(choice_id, choice_name) VALUES(0, "NO", -1.0);
INSERT INTO choice(choice_id, choice_name) VALUES(1, "LIKELY NO", -0.5);
INSERT INTO choice(choice_id, choice_name) VALUES(2, "NEUTRAL", 0.0);
INSERT INTO choice(choice_id, choice_name) VALUES(3, "LIKELY YES", +0.5);
INSERT INTO choice(choice_id, choice_name) VALUES(4, "YES", +1.0);

-- Question
CREATE TABLE question (
    question_id INT PRIMARY KEY,
    message VARCHAR(200)
);

-- Answer
CREATE TABLE answer (
    answer_id INT PRIMARY KEY,
    choice_id INT,
    CONSTRAINT ans_choice_id_fk FOREIGN KEY(choice_id) REFERENCES question(question_id)
);

-- Solution
CREATE TABLE solution (
    solution_id INT PRIMARY KEY,
    name VARCHAR(80)
);

-- Feature
CREATE TABLE feature (
    feature_id INT PRIMARY KEY,
    question_id INT,
    solution_id INT,
    choice_id INT,
    CONSTRAINT feat_question_id_fk FOREIGN KEY(question_id) REFERENCES question(question_id),
    CONSTRAINT feat_solution_id_fk FOREIGN KEY(solution_id) REFERENCES solution(solution_id),
    CONSTRAINT feat_choice_id_fk   FOREIGN KEY(choice_id)   REFERENCES choice(choice_id),
    UNIQUE feature_uniq (question_id, solution_id, choice_id)
);
