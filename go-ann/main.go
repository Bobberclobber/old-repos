package main

import (
	"fmt"
	"os"
)

// TODO: Add support for levels with differing number of neurons
func main() {
	// Set default parameters
	lNum := 3  // Number of levels
	nNum := 10 // Neurons per level

	parseInput(&lNum, &nNum)

	var network []*Level
	for i := 0; i < lNum; i++ {
		// Create a new level
		lvl := makeLevel(nNum)
		// Connect to the level above if one exists
		if i > 0 {
			addSynapses(network[i-1], &lvl)
		}
		// Add the level to the network
		network = append(network, &lvl)
	}

	drawNetwork(network)
}

/* Parse command line input */
func parseInput(lNum *int, nNum *int) {
	// Read command line input
	if len(os.Args[1:])%2 != 0 {
		panic(fmt.Sprintf("Invalid input format!"))
	} else if len(os.Args[1:]) > 0 {
		for i := 1; i < len(os.Args[1:]); i += 2 {
			// Number of levels specified
			if os.Args[i] == "-l" || os.Args[i] == "--levels" {
				val := stringToInt(os.Args[i+1])
				if val < 0 {
					panic(fmt.Sprintf("Invalid number of levels!"))
				}
				fmt.Printf("Number of levels specified: %d\n", val)
				*lNum = val
				// Number of neurons specified
			} else if os.Args[i] == "-n" || os.Args[i] == "--neurons" {
				val := stringToInt(os.Args[i+1])
				if val < 0 {
					panic(fmt.Sprintf("Invalid number of neurons!"))
				}
				fmt.Printf("Number of neurons specified: %d\n", val)
				*nNum = val
			} else {
				panic(fmt.Sprintf("Invalid input option: %s", os.Args[i]))
			}
		}
	}
}
