insert into uloge (ime) values ('ADMIN');
insert into uloge (ime) values ('KORISNIK');

insert into zanrovi (naziv) values ('naucna-fantastika');
insert into zanrovi (naziv) values ('tinejdž');
insert into zanrovi (naziv) values ('ljubavni');
insert into zanrovi (naziv) values ('istorijski');
insert into zanrovi (naziv) values ('klasici');
insert into zanrovi (naziv) values ('horor');
insert into zanrovi (naziv) values ('misterija');
insert into zanrovi (naziv) values ('erotika');
insert into zanrovi (naziv) values ('decije');

insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-89565-22-5', 'Crvena kraljica', '["Viktorija Ejvjard"]', 'Urban reads');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-6039-050-1', 'Bezimeni grad', '["Hauard F. Lavkraft"]', 'Orfelin');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-89565-29-4', 'Stakleni mac', '["Viktorija Ejvjard"]', 'Urban reads');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-10-02026-7', 'Stan u Parizu', '["Gijom Muso"]', 'Vulkan');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-521-3244-7', 'Crveni adresar', '["Sofija Lundberj"]', 'Laguna');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('9978-86-505-1624-9', 'Pad', '["Loren Kejt"]', 'Evro giunti');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-7710-654-6', 'Devojčice sa Olimpa - Zarobljenik Podzemnog sveta', '["Elena Kedros"]', 'Alnari');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-521-0147-4', 'Škola za princeze - Princeza Ema i lepa vila', '["Vivijan Frenč"]', 'Laguna');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-521-0146-7', 'Škola za princeze - Princeza Sofija i blistavo iznenađenje', '["Vivijan Frenč"]', 'Laguna');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-521-0145-0', 'Škola za princeze - Princeza Alisa i čarobno ogledalo', '["Vivijan Frenč"]', 'Laguna');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-10-02547-7', 'Deca zla', '["Miodrag Majić"]', 'Vulkan');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-521-0477-2', 'Maja Foks', '["Silvija Brena", "Inđino Strafi"]', 'Laguna');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-521-2584-5', 'Srećni ljudi čitaju i piju kafu', '["Anjes Marten - Ligan"]', 'Laguna');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-10-00444-1', 'Knjiga mrtvih duša', '["Glen Kuper"]', 'Vulkan');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-10-00659-9', 'Čuvari biblioteke', '["Glen Kuper"]', 'Vulkan');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-7710-486-3', 'Devojčice sa Olimpa - Moć snova', '["Elena Kedros"]', 'Alnari');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-7710-338-5', 'Devojčice sa Olimpa - Suze od kristala', '["Elena Kedros"]', 'Alnari');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-10-01542-3', 'Nebo pada', '["Sidni Šeldon"]', 'Vulkan');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-10-00765-7', 'Sjaj zvezda', '["Sidni Šeldon"]', 'Vulkan');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-521-3611-7', 'Majstor i Matgarita', '["Mihail Bulgakov"]', 'Laguna');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-521-3972-9', 'Evgenije Onjegin', '["Aleksandar Puškin"]', 'Laguna');
insert into knjige (isbn, naziv, pisci, izdavacka_kuca) values 
	('978-86-89565-02-7', 'Groznica tame', '["Karen Mari Moning"]', 'Urban reads');
	
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (1, 1);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (2, 1);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (6, 2);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (7, 2);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (1, 3);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (2, 3);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (7, 4);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (3, 5);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (4, 5);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (1, 6);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (2, 6);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (3, 6);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (1, 7);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (2, 7);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (9, 7);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (9, 8);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (9, 9);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (9, 10);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (7, 11);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (1, 12);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (2, 12);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (3, 13);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (6, 14);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (7, 14);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (6, 15);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (7, 15);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (1, 16);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (2, 16);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (9, 16);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (1, 17);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (2, 17);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (9, 17);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (7, 18);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (7, 19);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (5, 20);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (5, 21);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (1, 22);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (2, 22);
insert into zanrovi_knjiga (zanr_id, knjiga_id) values (8, 22);
	
insert into korisnici (korisnicko_ime, lozinka, ime, prezime, uloga_id) values
	('dumit', 'dumit', 'Milica', 'Đumić', 1);
insert into korisnici (korisnicko_ime, lozinka, ime, prezime, uloga_id) values
	('ana123', 'svecaneBeleKosulje', 'Anica', 'Dobra', 2);
insert into korisnici (korisnicko_ime, lozinka, ime, prezime, uloga_id) values
	('greenDay', 'misery', 'Makro', 'Savić', 2);
insert into korisnici (korisnicko_ime, lozinka, ime, prezime, uloga_id) values
	('nana2', 'NanA', 'Nataša', 'Nedić', 2);
insert into korisnici (korisnicko_ime, lozinka, ime, prezime, uloga_id) values
	('maja12', 'maja12', 'Maja', 'Malić', 2);
insert into korisnici (korisnicko_ime, lozinka, ime, prezime, uloga_id) values
	('ceca', 'ceca', 'Svetlana', 'Radić', 2);
insert into korisnici (korisnicko_ime, lozinka, ime, prezime, uloga_id) values
	('nindza', 'nindza', 'Nikola', 'Perić', 2);
insert into korisnici (korisnicko_ime, lozinka, ime, prezime, uloga_id) values
	('srbo', 'srbo', 'Srđan', 'Babić', 2);
insert into korisnici (korisnicko_ime, lozinka, ime, prezime, uloga_id) values
	('lela', 'lela', 'Laura', 'Tomić', 2);
insert into korisnici (korisnicko_ime, lozinka, ime, prezime, uloga_id) values
	('krle', 'krle', 'Konstantin', 'Kršić', 2);
insert into korisnici (korisnicko_ime, lozinka, ime, prezime, uloga_id) values
	('mile', 'mile', 'Milorad', 'Grbić', 2);

insert into korisnici_knjige (korisnik_id, knjiga_id) values (2, 4);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (2, 5);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (2, 13);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (3, 2);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (3, 14);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (3, 15);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (4, 1);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (4, 2);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (4, 20);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (5, 7);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (5, 10);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (5, 12);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (5, 16);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (5, 17);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (6, 1);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (6, 3);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (6, 4);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (6, 5);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (6, 6);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (6, 12);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (7, 1);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (7, 2);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (7, 11);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (7, 21);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (8, 20);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (8, 21);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (9, 5);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (9, 20);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (10, 2);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (10, 14);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (10, 18);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (10, 20);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (11, 1);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (11, 2);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (11, 3);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (11, 11);
insert into korisnici_knjige (korisnik_id, knjiga_id) values (11, 14);

insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (2, 3);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (2, 4);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (2, 7);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (3, 6);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (4, 1);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (4, 2);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (4, 3);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (4, 7);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (5, 9);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (6, 1);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (6, 3);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (7, 1);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (7, 5);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (7, 6);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (8, 5);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (9, 3);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (9, 5);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (10, 6);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (10, 7);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (11, 1);
insert into lajkovani_zanrovi (korisnik_id, zanr_id) values (11, 6);

