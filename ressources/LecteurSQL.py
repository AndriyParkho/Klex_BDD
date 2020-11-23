import sqlite3

# syntax : 
#
# connexion = sqlite3.connect("nom_de_la_base.db")
# cursor = connexion.cursor()
# connexion.execute("requette en sql en chaine de caractères")
# connexion.commit()
# connexion.execute("d'autres requettes")
# connexion.commit()
# connexion.close()


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
