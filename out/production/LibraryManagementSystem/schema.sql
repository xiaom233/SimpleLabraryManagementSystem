create table User
(
    id       integer identity,
    account varchar(20) unique not null,
    password varchar(20) not null,
    fullName varchar(20) not null,
    email varchar(20) not null,
    phoneNo varchar(20) not null,
    role varchar(10) not null,
    lock   boolean default false,
    primary key(id),
    registerTime timestamp

);

create table Book
(
    id       integer identity,
    isbn varchar(13) unique not null,
    bookName varchar(50) not null,
    author varchar(50),
    publisher varchar(50),
    publishTime varchar(30),
    description varchar(300),
    copyNumber integer default 0,
    remain integer default 0,
    bookCover varchar(100),
    primary key(id),
    delete boolean default false
);

create table BookItem
(
    id       integer identity,
    bookCode varchar(30) unique not null,
    bookId integer,
    foreign key(bookId) references Book(id),
    location varchar(30),
    status boolean  default false,
    delete boolean default false
);

create table BorrowingRequest
(
    id       integer identity,
    borrowerAccount varchar(30) not null,
    foreign key(borrowerAccount) references User(account),
    bookItemId integer not null,
    foreign key(bookItemId) references BookItem(id),
    status integer not null,
    startTime datetime not null,
    checkTime datetime,
    checkerId integer,
    foreign key(checkerId) references User(id)
);

create table BorrowingRecord
(
    id       integer identity,
    borrowerAccount varchar(30) not null,
    foreign key(borrowerAccount) references User(account),
    bookItemId integer not null,
    foreign key(bookItemId) references BookItem(id),
    status boolean not null default false,
    startTime datetime not null,
    endTime datetime not null
);




insert into User
values (1, 'root', 'root', 'ZHANGCHI', '452418146@qq.com', '18379533350', 'root',default,'2020-11-18 11:15:06.957');
insert into User
values (2, 'admin0001', 'admin0001', 'ZHANGCHI', '452418146@qq.com', '18379533350', 'admin',default ,'2020-11-18 11:15:06.957');
insert into User
values (3, '10001', '10001', 'ZHANGCHI', '452418146@qq.com', '18379533350', 'borrower',default ,'2020-11-18 11:15:06.957');
insert into User
values (4, '10002', '10002', 'ZHANGCHI', '452418146@qq.com', '18379533350', 'borrower',default ,'2020-11-18 11:15:06.957');
insert into User
values (5, '10003', '10002', 'ZHANGCHI', '452418146@qq.com', '18379533350', 'borrower',default ,'2020-11-18 11:15:06.957');
insert into User
values (6, '10004', '10002', 'ZHANGCHI', '452418146@qq.com', '18379533350', 'borrower',default ,'2020-11-18 11:15:06.957');
insert into User
values (7, '10005', '10002', 'ZHANGCHI', '452418146@qq.com', '18379533350', 'borrower',default ,'2020-11-18 11:15:06.957');
insert into User
values (8, '10006', '10002', 'ZHANGCHI', '452418146@qq.com', '18379533350', 'borrower',default ,'2020-11-18 11:15:06.957');
insert into Book
values (1, 'isbn1111', '算法设计', '王晓东', '清华出版社', '2020-11', '测试书1',2,1,'/cover/img1.png',default );
insert into Book
values (2, 'isbn2222', '网络与分布', '王小溪', '北大出版社', '2020-11', '测试书2',1 ,1,'/cover/img1.png',default );
insert into Book
values (3, 'isbn3222', '网络与分布', '王小溪', '北大出版社', '2020-11', '测试书2',1 ,1,'/cover/img1.png',default );
insert into Book
values (4, 'isbn4222', '网络与分布', '王小溪', '北大出版社', '2020-11', '测试书2',1 ,1,'/cover/img1.png',default );
insert into Book
values (5, 'isbn5222', '网络与分布', '王小溪', '北大出版社', '2020-11', '测试书2',1 ,1,'/cover/img1.png',default );
insert into Book
values (6, 'isbn6222', '网络与分布', '王小溪', '北大出版社', '2020-11', '测试书2',1 ,1,'/cover/img1.png',default );
insert into Book
values (7, 'isbn7222', '网络与分布', '王小溪', '北大出版社', '2020-11', '测试书2',1 ,1,'/cover/img1.png',default );
insert into Book
values (8, 'isbn7822', '网络与分布', '王小溪', '北大出版社', '2020-11', '测试书2',1 ,1,'/cover/img1.png',default );
insert into Book
values (9, 'isbn7922', '网络与分布', '王小溪', '北大出版社', '2020-11', '测试书2',1 ,1,'/cover/img1.png',default );
insert into Book
values (10, 'isbn7022', '网络与分布', '王小溪', '北大出版社', '2020-11', '测试书2',1 ,1,'/cover/img1.png',default );
insert into Book
values (11, 'isbn7212', '网络与分布', '王小溪', '北大出版社', '2020-11', '测试书2',1 ,1,'/cover/img1.png',default );

insert into BookItem
values(1,'isbn1111-1',1,'1F-A-1-1',false,false);
insert into BookItem
values(3,'isbn1111-2',1,'1F-A-1-2',true,false);
insert into BookItem
values(2,'isbn2222-1',2,'1F-A-2-2',true,false);

insert into BorrowingRequest
values(2,'10001',2,0,'2020-11-18 11:15:06.957','2020-11-18 11:15:06.957',null);
insert into BorrowingRequest
values(3,'10001',3,0,'2020-11-18 11:15:06.957','2020-11-18 11:15:06.957',null);

insert into BorrowingRecord
values(1,'10001',1,false,'2020-11-18 11:15:06.957','2020-11-18 11:15:06.957');
insert into BorrowingRecord
values(2,'10001',2,true,'2020-11-18 11:15:06.957','2020-11-18 11:15:06.957');
insert into BorrowingRecord
values(3,'10001',2,true,'2020-11-18 11:15:06.957','2020-11-18 11:15:06.957');
insert into BorrowingRecord
values(4,'10001',2,true,'2020-11-18 11:15:06.957','2020-11-18 11:15:06.957');
insert into BorrowingRecord
values(5,'10002',2,true,'2020-11-18 11:15:06.957','2020-11-18 11:15:06.957');
insert into BorrowingRecord
values(6,'10002',2,true,'2020-11-18 11:15:06.957','2020-11-18 11:15:06.957');
insert into BorrowingRecord
values(7,'10002',2,true,'2020-11-18 11:15:06.957','2020-11-18 11:15:06.957');
insert into BorrowingRecord
values(8,'10002',2,true,'2020-11-18 11:15:06.957','2020-11-18 11:15:06.957');
insert into BorrowingRecord
values(9,'10002',2,true,'2020-11-18 11:15:06.957','2020-11-18 11:15:06.957');
insert into BorrowingRecord
values(10,'10002',2,true,'2020-11-18 11:15:06.957','2020-11-18 11:15:06.957');
insert into BorrowingRecord
values(11,'10002',2,true,'2020-11-18 11:15:06.957','2020-11-18 11:15:06.957');
insert into BorrowingRecord
values(13,'10002',2,true,'2020-11-18 11:15:06.957','2020-11-18 11:15:06.957');