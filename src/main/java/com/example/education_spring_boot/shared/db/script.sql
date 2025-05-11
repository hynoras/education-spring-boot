INSERT INTO location (provincename, districtname, isremotearea) VALUES
('Hà Nội', 'Ba Đình', FALSE),
('Hồ Chí Minh', 'Quận 1', FALSE),
('Lào Cai', 'Bắc Hà', TRUE);
INSERT INTO department (departmentname) VALUES
('Công nghệ thông tin'),
('Kinh tế'),
('Y học');
INSERT INTO major (majorname, department_id) VALUES
('Khoa học máy tính', 1),
('Quản trị kinh doanh', 2),
('Y học cổ truyền', 3);
INSERT INTO account (username, password, email, role, createdat) VALUES
('finance1', '$2a$11$hPAIVEdO7s.LHkNqymolRemjNbMOZ0YlMP/GOlgKs3qrtm6aprT9u', 'finance1@example.com', 'FINANCE', NOW()),
('student1', '$2a$11$zoG7tWCg0leUVEJpEtH3heo25qMzEjZbI0AvEugW5WCBbedRETh46', 'student1@example.com', 'STUDENT', NOW())
INSERT INTO student (account_id, fullname, birthdate, gender, province_id, prioritygroup, major_id) VALUES
(4, 'Nguyễn Văn A', '2002-05-10', 'Male', 1, 'None', 1),
(6, 'Trần Thị B', '2001-08-20', 'Female', 2, 'Vùng sâu', 2);

