import sqlite3

conn = sqlite3.connect("Tap.db")


def initiate_leiding_table(conn):
    cursor = conn.cursor()
    cursor.execute("""CREATE TABLE leiding (
            first text,
            last text,
            schuld float
            )""")

def initiate_drank_table():
    cursor = conn.cursor()
    cursor.execute("""CREATE TABLE drank (
            naam text,
            voorraad integer,
            prijs real
            )""")

def generate_insert_leiding_statement(leider, connection):
    cursor = connection.cursor()
    statement = "INSERT INTO leiding VALUES ('{}', '{}', {})".format(leider[0], leider[1], leider[2])
    cursor.execute(statement)
    connection.commit()

def generate_insert_drank_statement(drank, connection):
    cursor = connection.cursor()
    statement = "INSERT INTO drank VALUES ('{}', {}, {})".format(drank[0], drank[2], drank[1])
    cursor.execute(statement)
    connection.commit()


def fill_leiding_table():
    file = open("SQLite/ChiroLeidingDB/leiding.csv", "r")

    for line in file:
        leider = line[:-1].split()
        print(leider)
        generate_insert_leiding_statement(leider, conn)

def fill_drank_table():
    file = open("SQLite/ChiroLeidingDB/drank.csv", "r")
    for line in file:
        drank = line[:-1].split()
        print(drank)
        generate_insert_drank_statement(drank, conn)



curs = conn.cursor()
initiate_leiding_table(conn)
fill_leiding_table()

initiate_drank_table()
fill_drank_table()

