import csv

class Solution:
    def estimate(self, date):
        dateArr = date.split("/")
        answer = ""
        if int(dateArr[2][0]) > 24 or (int(dateArr[0]) > 9 and int(dateArr[2]) >= 24):
            if int(dateArr[2][0]) == 24:
                answer = "1.0" + str(325 / 12 * (12 - int(dateArr[0])) + 325 / 365 * int(dateArr[1]))
            else:
                answer = "1.0" + str(325 / 12 * (3 + int(dateArr[0])) + 325 / 365 * int(dateArr[1]))
        else:
            with open("Nat_Gas.csv", "r") as file:
                reader = csv.reader(file, delimiter="/")
                for row in reader:
                    try:
                        if row[0] == dateArr[0] and row[2] == dateArr[2]:
                            dif = float(row[2].split(",")[1][:3]) - float(next(reader)[2].split(",")[1][:3])
                            n = float(next(reader)[1])
                            answer = "1" + str((dif / n) * (float(dateArr[1]) / n))
                            break
                    except:
                        continue
        return answer
