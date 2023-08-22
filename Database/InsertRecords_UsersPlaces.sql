use TIMS;

 #Inserting Users#
insert into Users
values ('001', 'adminsss', 'Admin');
insert into Users
values ('002', 'rita123', 'Traveler');
insert into Users
values ('003', 'ed002', 'Guide');

 #Inserting Traveler#
insert into Traveler
values ('002', 'Rita', 25, '23/45, 1st Cross Street, Bodhi Nagar, Velachery, Chennai, Tamil nadu - 62', 'Female', '9932551678', 'rita123@gmail.com');

 #Inserting Admin#
insert into Admin 
values ('001', 'Samyuktha', '9832252339', 'samgreg@gmail.com');

 #Inserting Guide#
insert into Guide
values ('003', 'Edward', '7667587790', 'edthomas@gmail.com', 'Certifications like CTG(Certified Tour Guide), CPTG(Certified Professional Tour Guide)');

 #Inserting Location#
insert into Location
values ('l_01', 'Delhi', 'India');
insert into Location
values ('l_02', 'Mumbai', 'India');
insert into Location
values ('l_03', 'Pondicherry', 'India'); 
insert into Location
values ('l_04', 'Jaipur', 'India');
insert into Location
values ('l_05', 'Manali', 'India');

 #Inserting Places#
insert into Places
values ('Qutub Minar', 'l_01', 'Historical');
insert into Places
values ('India Gate', 'l_01', 'Historical');
insert into Places
values ('Red Fort', 'l_01', 'Historical');
insert into Places
values ('Jantar Mantar', 'l_01', 'Historical');
insert into Places
values ('Rashtrapathi Bhavan', 'l_01', 'Historical');
insert into Places
values ('Humayun''s Tomb', 'l_01', 'Historical');
insert into Places
values ('Akshardam Temple', 'l_01', 'Historical');
insert into Places
values ('Jama Masjid', 'l_01', 'Historical | Eatery');
insert into Places
values ('Iron Pillar', 'l_01', 'Historical');
insert into Places
values ('Hauz Khaz Village', 'l_01', 'Historical | Scenic | Recreational');
insert into Places
values ('National Zoological Park', 'l_01', 'Recreational');
insert into Places
values ('National Rail Museum', 'l_01', 'Recreational');
insert into Places
values ('Garden of Five Senses', 'l_01', 'Recreational | Scenic');
insert into Places
values ('Chandni Chowk', 'l_01', 'Recreational | Eatery');
insert into Places
values ('Lodhi Garden', 'l_01', 'Recreational | Scenic');
insert into Places
values ('Connaught Place', 'l_01', 'Recreational | Eatery');
insert into Places
values ('Pacific Mall', 'l_01', 'Recreational');
insert into Places
values ('Dilli Haat', 'l_01', 'Recreational | Eatery');
insert into Places
values ('Janpath Market', 'l_01', 'Recreational');
insert into Places
values ('Khan Market', 'l_01', 'Recreational');
insert into Places
values ('Paharganj', 'l_01', 'Recreational');
insert into Places
values ('Paranthe Wali Galli', 'l_01', 'Eatery');
insert into Places
values ('South Campus', 'l_01', 'Eatery');
insert into Places
values ('Hudson Lane', 'l_01', 'Eatery');
insert into Places
values ('Old Delhi', 'l_01', 'Eatery');
insert into Places
values ('Nizamuddin', 'l_01', 'Eatery');

insert into Places
values ('Gateway of India', 'l_02', 'Historical');
insert into Places
values ('Shree Siddhivinayak Temple', 'l_02', 'Historical');
insert into Places
values ('Basilica of Our Lady of the Mount', 'l_02', 'Historical');
insert into Places
values ('Mount Mary Basilica', 'l_02', 'Historical');
insert into Places
values ('Sri Sri Radha Gopinath Temple', 'l_02', 'Historical');
insert into Places
values ('Sanjay Gandhi National Park', 'l_02', 'Recreational');
insert into Places
values ('Jehangir Art Gallery', 'l_02', 'Recreational');
insert into Places
values ('Colaba Causeway', 'l_02', 'Recreational | Eatery');
insert into Places
values ('Global Vippasana Pagoda', 'l_02', 'Recreational');
insert into Places
values ('Nehru Planetarium', 'l_02', 'Recreational');
insert into Places
values ('High Street Phoenix', 'l_02', 'Recreational');
insert into Places
values ('Prithvi Theatre', 'l_02', 'Recreational');
insert into Places
values ('Oberoi Mall', 'l_02', 'Recreational');
insert into Places
values ('Imagicaa', 'l_02', 'Recreational | Adventure');
insert into Places
values ('Essel World', 'l_02', 'Adventure | Recreational');
insert into Places
values ('KidZania', 'l_02', 'Adventure');
insert into Places
values ('Snow Kingdom', 'l_02', 'Adventure');
insert into Places
values ('Kanheri Caves', 'l_02', 'Scenic | Recreational | Adventure');
insert into Places
values ('Elephanta Caves', 'l_02', 'Scenic | Recreational | Adventure');
insert into Places
values ('Hanging Garden', 'l_02', 'Scenic | Recreational');
insert into Places
values ('Bandra Worli Sea Link', 'l_02', 'Scenic');
insert into Places
values ('Marine Drive', 'l_02', 'Scenic | Recreational | Eatery');
insert into Places
values ('Juhu Beach', 'l_02', 'Scenic');
insert into Places
values ('Chowpatty Beach', 'l_02', 'Scenic');
insert into Places
values ('Gaylord', 'l_02', 'Eatery');
insert into Places
values ('Pizza By The Bay', 'l_02', 'Eatery');
insert into Places
values ('Aram Vadapav', 'l_02', 'Eatery');
insert into Places
values ('Bademia', 'l_02', 'Eatery');
insert into Places
values ('Cafe Leopold', 'l_02', 'Eatery');
insert into Places
values ('Stadium Restaurant', 'l_02', 'Eatery');

insert into Places
values ('The Basilica of the Sacred Heart of Jesus', 'l_03', 'Historical');
insert into Places
values ('Arulmigu Manakula Vinayagar Devasthanam', 'l_03', 'Historical');
insert into Places
values ('Eglise de Notre Dame des Anges', 'l_03', 'Historical');
insert into Places
values ('Immaculate Conception Cathedral', 'l_03', 'Historical');
insert into Places
values ('Arikamedu', 'l_03', 'Historical');
insert into Places
values ('The Statue of Dupleix', 'l_03', 'Historical');
insert into Places
values ('Sri Aurobindo Ashram', 'l_03', 'Recreational');
insert into Places
values ('Puducherry Museum', 'l_03', 'Recreational');
insert into Places
values ('French War Memorial', 'l_03', 'Recreational');
insert into Places
values ('Botanical Garden', 'l_03', 'Recreational');
insert into Places
values ('White Town', 'l_03', 'Recreational');
insert into Places
values ('Casablanca', 'l_03', 'Recreational');
insert into Places
values ('SUND''art', 'l_03', 'Recreational');
insert into Places
values ('Studio Ebony', 'l_03', 'Recreational');
insert into Places
values ('Anokhi', 'l_03', 'Recreational');
insert into Places
values ('Kalki', 'l_03', 'Recreational');
insert into Places
values ('La Boutique D''Auroville', 'l_03', 'Recreational');
insert into Places
values ('Saram China Market', 'l_03', 'Recreational');
insert into Places
values ('Nehru Street Market', 'l_03', 'Recreational');
insert into Places
values ('Providence Mall', 'l_03', 'Recreational');
insert into Places
values ('Hi-Design', 'l_03', 'Recreational');
insert into Places
values ('Serenity Beach', 'l_03', 'Scenic | Recreational');
insert into Places
values ('Rock Beach', 'l_03', 'Scenic');
insert into Places
values ('Promenade Beach', 'l_03', 'Scenic');
insert into Places
values ('Auroville', 'l_03', 'Scenic | Recreational');
insert into Places
values ('Chunnambar Boat House', 'l_03', 'Adventure');
insert into Places
values ('Paradise Beach', 'l_03', 'Adventure | Scenic');
insert into Places
values ('Pogo Land', 'l_03', 'Adventure');
insert into Places
values ('Cafe des Arts', 'l_03', 'Eatery');
insert into Places
values ('Le Dupleix', 'l_03', 'Eatery');
insert into Places
values ('Crepe In Touch', 'l_03', 'Eatery');
insert into Places
values ('Villa Shanthi', 'l_03', 'Eatery');
insert into Places
values ('Bay of Buddha', 'l_03', 'Eatery');
insert into Places
values ('Coromandel Cafe', 'l_03', 'Eatery');
insert into Places
values ('Cafe Xtasi', 'l_03', 'Eatery');
insert into Places
values ('Zuka Cafe', 'l_03', 'Eatery');

insert into Places
values ('Hawa Mahal', 'l_04', 'Historical');
insert into Places
values ('The City Palace', 'l_04', 'Historical');
insert into Places
values ('Nahargarh Fort', 'l_04', 'Historical | Adventure');
insert into Places
values ('Amber Palace', 'l_04', 'Historical');
insert into Places
values ('Jal Mahal', 'l_04', 'Historical');
insert into Places
values ('Birla Mandir', 'l_04', 'Historical');
insert into Places
values ('Jaigarh Fort', 'l_04', 'Historical | Recreational');
insert into Places
values ('Amer Fort', 'l_04', 'Historical | Adventure');
insert into Places
values ('Albert Hall Museum', 'l_04', 'Recreational');
insert into Places
values ('Sisodia Rani ka Bagh', 'l_04', 'Recreational | Scenic');
insert into Places
values ('Ramniwas Bagh', 'l_04', 'Recreational | Scenic');
insert into Places
values ('Central Park', 'l_04', 'Recreational');
insert into Places
values ('Jhalana Safari Park', 'l_04', 'Adventure | Recreational');
insert into Places
values ('Jaipur Desert Safari ', 'l_04', 'Adventure | Recreational');
insert into Places
values ('Kukas', 'l_04', 'Adventure');
insert into Places
values ('Sambhar Lake', 'l_04', 'Scenic');
insert into Places
values ('Laxmi Mishthan Bhandar', 'l_04', 'Eatery');
insert into Places
values ('Gulab Ji Chaiwala', 'l_04', 'Eatery');
insert into Places
values ('Masala Chowk', 'l_04', 'Eatery');
insert into Places
values ('Pandit''s', 'l_04', 'Eatery');
insert into Places
values ('The Rajput Room', 'l_04', 'Eatery | Recreational');
insert into Places
values ('Zolocrust', 'l_04', 'Eatery');
insert into Places
values ('Jaipur Adda', 'l_04', 'Eatery');
insert into Places
values ('Tapri Central', 'l_04', 'Eatery');

insert into Places
values ('Hadimba Devi Temple', 'l_05', 'Historical');
insert into Places
values ('Manu Temple Road', 'l_05', 'Historical | Recreational');
insert into Places
values ('Himalayan Nyinmapa Buddhist Monastery', 'l_05', 'Historical');
insert into Places
values ('Naggar Castle', 'l_05', 'Historical');
insert into Places
values ('Manikaran Gurudwara', 'l_05', 'Historical');
insert into Places
values ('Mall Road', 'l_05', 'Recreational');
insert into Places
values ('Sultanpur Market', 'l_05', 'Recreational');
insert into Places
values ('Bhuttico', 'l_05', 'Recreational');
insert into Places
values ('Tibetan Market', 'l_05', 'Recreational');
insert into Places
values ('Himachal Emporium', 'l_05', 'Recreational');
insert into Places
values ('Old Manali', 'l_05', 'Recreational | Scenic');
insert into Places
values ('Manu Market', 'l_05', 'Recreational');
insert into Places
values ('Manali Sanctuary', 'l_05', 'Recreational');
insert into Places
values ('Vashisht Baths', 'l_05', 'Recreational');
insert into Places
values ('Museum of Himachal Culture and Folk Art', 'l_05', 'Recreational');
insert into Places
values ('Great Himalayan National Park', 'l_05', 'Recreational');
insert into Places
values ('Rani Nala', 'l_05', 'Scenic');
insert into Places
values ('Nehru Kund', 'l_05', 'Scenic | Recreational');
insert into Places
values ('Jogni Waterfall', 'l_05', 'Scenic');
insert into Places
values ('Rohtang La', 'l_05', 'Scenic | Adventure');
insert into Places
values ('Bhrigu Lake', 'l_05', 'Scenic');
insert into Places
values ('Hamta Pass', 'l_05', 'Scenic');
insert into Places
values ('Beas River', 'l_05', 'Scenic');
insert into Places
values ('Jana Waterfall', 'l_05', 'Scenic');
insert into Places
values ('Gulaba ', 'l_05', 'Scenic');
insert into Places
values ('Pandoh Dam', 'l_05', 'Scenic | Recreational');
insert into Places
values ('Friendship Peak', 'l_05', 'Scenic');
insert into Places
values ('Beas Kund', 'l_05', 'Adventure');
insert into Places
values ('Himvalley Manali Amusement and Culture Park', 'l_05', 'Adventure');
insert into Places
values ('Solang Valley', 'l_05', 'Adventure');
insert into Places
values ('Desi Swaad', 'l_05', 'Eatery');
insert into Places
values ('Cafe 1947', 'l_05', 'Eatery');
insert into Places
values ('Mount View Restaurant', 'l_05', 'Eatery');
insert into Places
values ('Il Forno', 'l_05', 'Eatery');
insert into Places
values ('La Plage', 'l_05', 'Eatery');
insert into Places
values ('Renaissance Manali', 'l_05', 'Eatery');
insert into Places
values ('Fat Plate Restaurant', 'l_05', 'Eatery');
