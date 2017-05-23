package main

import (
	"fmt"
	"math"
	"math/rand"
	"strconv"
	"time"
)

var counter = -1

func newID() int {
	counter++
	return counter
}

/* Return num unique random integers in range [0,n)*/
func randIx(num int, n int) []int {
	// Invalid inputs
	if num <= 0 {
		panic(fmt.Sprintf("num <= 0"))
	}
	if n <= 0 {
		panic(fmt.Sprintf("n <= 0"))
	}
	if n < num {
		panic(fmt.Sprintf("n < num"))
	}

	// Generate random numbers
	rand.Seed(time.Now().UnixNano())
	var rArr []int
	for i := 0; i < num; i++ {
		r := rand.Intn(n)
		for contains(rArr, r) {
			r = rand.Intn(n)
		}
		rArr = append(rArr, r)
	}
	return rArr
}

/* Return true if arr contains num */
func contains(arr []int, num int) bool {
	for i := 0; i < len(arr); i++ {
		if arr[i] == num {
			return true
		}
	}
	return false
}

/* Generate a random, non-negative, normal distributed with the given limits ([min,max]) */
func normInt(min int, max int) int {
	// Invalid inputs
	if min < 0 {
		panic(fmt.Sprintf("min < 0"))
	}
	if max <= min {
		panic(fmt.Sprintf("max <= min"))
	}
	// Type conversion
	minf := float64(min)
	maxf := float64(max)
	// Calculate appropriate mean and standard deviation
	mean := (minf + maxf) / 2
	sd := (mean - minf) / 3
	// Generate a random, normal distributed float and clamp it
	rand.Seed(time.Now().UnixNano())
	r := rand.NormFloat64()*sd + mean
	r = math.Min(math.Max(r, minf), maxf)
	return int(math.Floor(r + 0.5))
}

/* Wrapper for error handling the strconv function */
func stringToInt(str string) int {
	val, err := strconv.Atoi(str)
	if err != nil {
		panic(fmt.Sprint(err))
	}
	return val
}
