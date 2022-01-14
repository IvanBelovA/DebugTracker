INSERT INTO user (name, last_name, speciality)
VALUES
('Ivan', 'Petrov', 'backend developer'),
('Petr', 'Ivanov', 'frontend develper'),
('Maria', 'Frolova', 'designer');

INSERT INTO project (name)
VALUES
('Internet shop'),
('Web application');

INSERT INTO priority (name)
VALUES
('Urgently'),
('Avarage'),
('Not urgent');

INSERT INTO topic (title, description)
VALUES
('Fix bug', 'Fix bug in application'),
('New feature', 'Added new feature in application');

INSERT INTO type (title)
VALUES
('Backend'),
('Frontend'),
('Design');

INSERT INTO task (project_id, topic_id, type_id, priority_id, user_id, description)
VALUES
(1, 2, 2, 2, 2, 'Add new button'),
(1, 1, 1, 1, 1, 'Items are not added to the cart'),
(2, 2, 3, 3, 3, 'Develop a design for web application'),
(1, 1, 2, 1, 2, 'Buttons on the home page stuck together');
