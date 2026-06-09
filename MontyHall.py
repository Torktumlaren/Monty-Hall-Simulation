import random
import time

DEBUG = False

ITERATIONS = 10_000_000


def probabilityPercent(a, b):
    return (a / b) * 100


successesStay = 0
successesSwitch = 0

startTime = time.time()

for _ in range(ITERATIONS):
    doors = [False]*3

    doors[random.randint(0,2)] = True

    if DEBUG:
        print(f"Door Truth: {doors}")

    # Pick a door
    choiceContenstant = random.randint(0,2)
    if DEBUG:
        print(f"Contestant chose door #{choiceContenstant + 1}")

    if doors[(choiceContenstant + 1) % 3]:
        choiceHost = (choiceContenstant - 1) % 3
    else:
        choiceHost = (choiceContenstant + 1) % 3

    if DEBUG:
        print(f"Host revealed door #{choiceHost + 1}")

    # Stay
    if doors[choiceContenstant]:
        successesStay += 1
        if DEBUG:
            print("Winning strategy: STAY")

    if doors[3 - (choiceContenstant + choiceHost)]:
        successesSwitch += 1
        if DEBUG:
            print("Winning strategy: SWITCH")
    if DEBUG:
        print("---------------\n")

stopTime = time.time()

probabilityStay = probabilityPercent(successesStay, ITERATIONS)
probabilitySwitch = probabilityPercent(successesSwitch, ITERATIONS)

print(f"Probability of success when staying: {probabilityStay}%")
print(f"Probability of success when switching: {probabilitySwitch}%")

print(f"\n{ITERATIONS} iterations completed in {(stopTime-startTime)*1000} milliseconds.\n")
