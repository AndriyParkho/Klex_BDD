import sqlite3



conn = sqlite3.connect("base.db")

cursor = conn.cursor()

with open("test.sql", "r") as f:
    end = False
    txt = f.readline()
    sql = ""
    while txt != "":
        if ";" in txt:
            end = not end

        sql += txt


        if end:
            print("new state : ", end = "")
            print(sql)
            cursor.execute(sql)
            conn.commit()
            end = not end
            sql = ""

        txt = f.readline()


conn.close()
