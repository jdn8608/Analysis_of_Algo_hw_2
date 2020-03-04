import math

'''
Name: Joseph Nied
Date: 2/12/20
Class: CSC-261
Question: 3
Description: Algorithm to see if given a points, if any would lie on a sphere together
 '''

def main() -> None:
    SIZE = int(input())
    #Create an empty array with size SIZE
    Magnitude = [None] * SIZE

    #Get the input coordinates
    for i in range(SIZE):
        inputVal = input()
        list = inputVal.split()
        temp_x = int(list[0])
        temp_y = int(list[1])
        temp_z = int(list[2])

        #Enter in the magnitude^2 for each point (this stays an int)
        Magnitude[i] = int((math.pow(temp_x, 2) + math.pow(temp_y, 2) + math.pow(temp_z, 2)))


    determind(Magnitude, SIZE)


def determind(Magnitude, size):
    #Determine the max value in magnitude
    max = Magnitude[0]
    for x in range(1, len(Magnitude)):
        if max < Magnitude[x]:
            max = Magnitude[x]

    #Sort the data in about O(n) time:
    radixSort(Magnitude, max, size)

    #loop through the array in O(n) time
    #See if any neighbors are equal, if so, they lie on a sphere together
    answer = 'NO';
    for i in range(len(Magnitude)-1):
        if Magnitude[i] == Magnitude[i+1]:
            answer = 'YES'
    print(answer)

def countSort(Magnitude, power,size):
    #answer array that will hold the sorted data
    answer = [0] * len(Magnitude)
    #empty array that will contain the counts
    count = [0] * size

    #counts the occureces of a #//power % size
    #this is specifc for radixsort
    for i in range(len(Magnitude)):
        digitValue = (Magnitude[i]//power)%size
        count[digitValue]+=1

    #makes the count array cummalative
    for j in range(1,len(count)):
        count[j] = count[j-1] + count[j]

    #loops through backwards and slowly fills the answer array given the count array
    k = len(Magnitude)-1
    while k >=0:
        digitValue = (Magnitude[k]//power)%size
        answer[count[digitValue] - 1] = Magnitude[k]
        count[digitValue] -= 1
        k-=1

    #Convert Magnitude to the answer array
    for l in range(len(Magnitude)):
        Magnitude[l] = answer[l]

def radixSort(Mag, maxVal,size):
    place = 1
    while (maxVal/place) > 0:
        countSort(Mag, place, size)
        #increment place but a multiplicity of size
        place = place*size


if __name__ == '__main__':
    main()