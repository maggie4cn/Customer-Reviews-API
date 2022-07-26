CREATE TABLE products (
                          productID INT AUTO_INCREMENT,
                          productName VARCHAR(200) NOT NULL,
                          PRIMARY KEY (productID)
);

CREATE TABLE reviews (
                         reviewID INT AUTO_INCREMENT,
                         reviewContent VARCHAR(1000) NOT NULL,
                         productID INT NOT NULL,
                         CONSTRAINT prod_review FOREIGN KEY (productID) REFERENCES products (productID),
                         PRIMARY KEY (reviewID)
);

CREATE TABLE comments (
                          commentID INT AUTO_INCREMENT,
                          commentContent VARCHAR(1000) NOT NULL,
                          reviewID INT NOT NULL,
                          CONSTRAINT review_comment FOREIGN KEY (reviewID) REFERENCES reviews (reviewID),
                          PRIMARY KEY (commentID)
);