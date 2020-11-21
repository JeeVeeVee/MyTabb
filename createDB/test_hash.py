import random;

def hash(a, b, c, d):
    return (a * b * c + d + a * b * d + c + a * c * d + b + a + b *  c * d) % 232


print(hash(9,9,9,9))

checker = [0 for i in range(2953)]
for i in range(10):
    for j in range(10):
        for k in range(10):
            for l in range(10):
                checker[hash(i, j, k, l)] += 1


def generate_random_valid_hash():
    output = random.randint(0, 2953+1)
    if (checker[output] > 0):
        return output
    else:
        
        return generate_random_valid_hash()


file = open("createDB/leiding.csv", "r")
for line in file:
    print(line[:-1] + "\t" + str(generate_random_valid_hash())) 

