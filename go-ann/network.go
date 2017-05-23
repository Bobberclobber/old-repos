package main

import (
	"math/rand"
	"time"
)

// A Level contains several Neurons
type Level struct {
	neurons []*Neuron
}

/* Create a new level of given size */
func makeLevel(size int) Level {
	lvl := Level{}
	for i := 0; i < size; i++ {
		lvl.neurons = append(lvl.neurons, &Neuron{})
		lvl.neurons[i].id = newID()
	}
	return lvl
}

/* Create random synapses between two levels */
func addSynapses(uLvl *Level, lLvl *Level) {
	for i := 0; i < len(uLvl.neurons); i++ {
		// Randomize children
		// Choose a random number of children ([1,len(lLvl)-1])
		num := normInt(1, len(lLvl.neurons)-1)
		cix := randIx(num, len(lLvl.neurons))
		for j := 0; j < len(cix); j++ {
			addSynapse(uLvl.neurons[i], lLvl.neurons[cix[j]])
		}
	}
	// Make sure no neuron in the lower level is without a parent
	rand.Seed(time.Now().UnixNano())
	for i := 0; i < len(lLvl.neurons); i++ {
		if len(lLvl.neurons[i].parents) == 0 {
			// Choose a random number of parents ([1,len(uLvl)-1])
			num := normInt(1, len(uLvl.neurons)-1)
			pix := randIx(num, len(uLvl.neurons))
			for j := 0; j < len(pix); j++ {
				addSynapse(uLvl.neurons[pix[j]], lLvl.neurons[i])
			}
		}
	}
}

/* Add a synapse between the neurons */
func addSynapse(parent *Neuron, child *Neuron) {
	s := parent.addChild(child, nil)
	child.addParent(parent, s)
}
