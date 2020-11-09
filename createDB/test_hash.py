checktabel = [0 for i in range(40)]
for i in range(10):
    for j in range(10):
        for k in range(10):
            for l in range(10):
                checktabel[i + j + k + l] += 1

print(checktabel)
for i in range(len(checktabel)):
    print(checktabel[i])
