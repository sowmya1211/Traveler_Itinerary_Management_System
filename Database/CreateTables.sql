create database TIMS; 

use TIMS;

 #Creating Users relation#
create table Users(
uid varchar(10),
password varchar(30) NOT NULL, 
utype varchar(20) NOT NULL, 
constraint uid_pk PRIMARY KEY(uid),
constraint utype_ch check (utype in('Traveler', 'Admin', 'Guide'))
);


 #Creating Traveler relation#
create table Traveler(
tid varchar(10),
name varchar(30) NOT NULL,
age integer,
address varchar(250),
sex varchar(20),
phno varchar(10) NOT NULL,
email varchar(30) NOT NULL,
constraint tid_pk PRIMARY KEY(tid),
constraint tid_fk_uid FOREIGN KEY (tid) REFERENCES Users (uid) ON DELETE CASCADE
);


#Creating Admin relation#
create table Admin(
aid varchar(10),
name varchar(30) NOT NULL,
phno varchar(10) NOT NULL,
email varchar(30) NOT NULL,
constraint aid_pk PRIMARY KEY(aid),
constraint aid_fk_uid FOREIGN KEY (aid) REFERENCES Users(uid) ON DELETE CASCADE
);


 #Creating Guide relation#
create table Guide(
gid varchar(10),
name varchar(30) NOT NULL,
phno varchar(10) NOT NULL,
email varchar(30) NOT NULL,
proof varchar(255) NOT NULL,
constraint gid_pk PRIMARY KEY(gid),
constraint gid_fk_uid FOREIGN KEY (gid) REFERENCES Users(uid) ON DELETE CASCADE
);


 #Creating Location relation#
create table Location(
loc_id varchar(10),
city varchar(50) NOT NULL,
country varchar(50) NOT NULL,
constraint locid_pk PRIMARY KEY(loc_id)
);


 #Creating Transit relation#
create table Transit(
transit_id varchar(10),
trans_type varchar(10) NOT NULL,
source varchar(30) NOT NULL,
dest varchar(30) NOT NULL,
start_time time NOT NULL,
end_time time NOT NULL,
cost float NOT NULL,
prom_priority integer,
constraint transid_pk PRIMARY KEY(transit_id),
constraint transtype_ch check (trans_type in('Flight', 'Train'))
);


 #Creating Accommodation relation#
create table Accommodation(
accom_id varchar(10),
loc_id varchar(10),
accom_name varchar(30) NOT NULL,
accom_type varchar(10) NOT NULL,
address varchar(255),
cost float NOT NULL,
amenities varchar(255),
prom_priority integer,
constraint accomid_pk PRIMARY KEY(accom_id),
constraint accom_fk_locid FOREIGN KEY (loc_id) REFERENCES Location(loc_id),
constraint accomtype_ch check (accom_type in('Hotel', 'Villa', 'Homestay', 'Hostel', 'Resort'))
);

#Creating Places relations #
create table Places(
    place_name varchar(70),
    loc_id varchar(10),
    place_type varchar(50),
    constraint place_pk PRIMARY KEY(place_name),
    constraint place_fk_locid FOREIGN KEY (loc_id) REFERENCES Location(loc_id)
);

 #Creating Trips relation#
create table Trips(
trip_id varchar(10),
traveler_id varchar(10) NOT NULL,
st_place varchar(30) NOT NULL,
dest varchar(30) NOT NULL,
st_date date NOT NULL,
end_date date NOT NULL,
onw_trans_id varchar(10),
ret_trans_id varchar(10),
accom_id varchar(10),
constraint tripid_pk PRIMARY KEY(trip_id),
constraint trips_fk_tid FOREIGN KEY (traveler_id) REFERENCES Traveler(tid) ON DELETE CASCADE,
constraint trips_fk_onwtrid FOREIGN KEY (onw_trans_id) REFERENCES Transit(transit_id),
constraint trips_fk_rettrid FOREIGN KEY (ret_trans_id) REFERENCES Transit(transit_id),
constraint trips_fk_accid FOREIGN KEY (accom_id) REFERENCES Accommodation(accom_id)
);


 #Creating Itineraries relation#
create table Itineraries(
trip_id varchar(10),
it_id varchar(10),
tourist_spot varchar(70),
dov date NOT NULL,
st_time time NOT NULL,
duration integer,
constraint trip_pk_it PRIMARY KEY (trip_id, it_id),
constraint it_fk_tripid FOREIGN KEY (trip_id) REFERENCES Trips(trip_id) ON DELETE CASCADE,
constraint it_fk_place FOREIGN KEY (tourist_spot) REFERENCES Places(place_name)
);

 #Creating Recommendations relation#
create table Recommendations(
rec_no integer,
guide_id varchar(10),
locn varchar(30) NOT NULL,
recs varchar(255) NOT NULL,
constraint recno_pk_gid PRIMARY KEY (rec_no, guide_id),
constraint rec_fk_gid FOREIGN KEY (guide_id) REFERENCES Guide(gid) ON DELETE CASCADE,
constraint rec_fk_locid FOREIGN KEY (locn) REFERENCES Location(loc_id)
);

 #Creating Checklist relation#
create table Checklist(
checklist_id varchar(10),
trip_id varchar(10),
checklist_name varchar(30) NOT NULL,
constraint chkid_pk_tripid PRIMARY KEY (checklist_id, trip_id),
constraint chk_fk_tripid FOREIGN KEY (trip_id) REFERENCES Trips(trip_id) ON DELETE CASCADE
);

 #Creating ChecklistItems relation#
create table ChecklistItems(
checklist_item_no integer,
checklist_id varchar(10),
item_name varchar(50) NOT NULL,
tickstatus varchar(20),
constraint chkitem_pk_chkid PRIMARY KEY (checklist_item_no, checklist_id),
constraint chkit_fk_chkid FOREIGN KEY (checklist_id) REFERENCES Checklist(checklist_id) ON DELETE CASCADE,
constraint tickstatus_ch check (tickstatus in('Checked', 'Unchecked'))
);

 #Creating Budget relation#
create table Budget(
budget_id varchar(10),
trip_id varchar(10),
group_name varchar(30),
amt float NOT NULL,
constraint budgetid_pk_tripid PRIMARY KEY (budget_id, trip_id),
constraint budget_fk_tripid FOREIGN KEY (trip_id) REFERENCES Trips(trip_id) ON DELETE CASCADE
);


 #Creating Expenses relation#
create table Expenses(
exp_no integer,
budget_id varchar(10),
trip_id varchar(10),
exp_dets varchar(50) NOT NULL,
amt_spent float NOT NULL,
constraint exp_pk_budgetid PRIMARY KEY (exp_no, budget_id,trip_id),
constraint exp_fk_tripid FOREIGN KEY (trip_id) REFERENCES Trips(trip_id) ON DELETE CASCADE,
constraint exp_fk_budgetid FOREIGN KEY (budget_id) REFERENCES Budget(budget_id) ON DELETE CASCADE
);

 #Creating Chats relation#
create table Chats(
chat_id varchar(10),
traveler_id varchar(10) NOT NULL,
admin_id varchar(10) NOT NULL,
constraint chatid_pk PRIMARY KEY(chat_id),
constraint chats_fk_tid FOREIGN KEY (traveler_id) REFERENCES Traveler(tid),
constraint chats_fk_aid FOREIGN KEY (admin_id) REFERENCES Admin(aid) 
);

 #Creating Messages relation#
create table Messages(
msg_no integer,
chat_id varchar(10),
sender varchar(30) NOT NULL,
receiver varchar(30) NOT NULL,
msg varchar(255) NOT NULL,
sent_date date NOT NULL,
sent_time time NOT NULL,
msgstatus varchar(20),
constraint msg_pk_chatid PRIMARY KEY (msg_no, chat_id),
constraint msg_fk_chatid FOREIGN KEY (chat_id) REFERENCES Chats(chat_id) ON DELETE CASCADE,
constraint msgstatus_ch check (tickstatus in('Sent', 'Seen'))
);
