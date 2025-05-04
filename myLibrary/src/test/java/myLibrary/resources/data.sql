-- src/main/resources/data.sql
INSERT INTO authors (name, nationality) VALUES
('John Smith', 'American'),
('Jane Austen', 'British'),
('Gabriel Garcia Marquez', 'Colombian'),
('Haruki Murakami', 'Japanese'),
('J.K. Rowling', 'British'),
('Ernest Hemingway', 'American'),
('Toni Morrison', 'American'),
('Salman Rushdie', 'British-Indian'),
('Isabel Allende', 'Chilean'),
('George Orwell', 'British');

INSERT INTO books (title, isbn, author_id) VALUES
('Book One', '1234567890', 1),
('Pride and Prejudice', '1234567891', 2),
('One Hundred Years of Solitude', '1234567892', 3),
('Norwegian Wood', '1234567893', 4),
('Harry Potter', '1234567894', 5),
('The Old Man and the Sea', '1234567895', 6),
('Beloved', '1234567896', 7),
('Midnight''s Children', '1234567897', 8),
('The House of the Spirits', '1234567898', 9),
('1984', '1234567899', 10);