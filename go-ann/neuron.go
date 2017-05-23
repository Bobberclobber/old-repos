package main

import "math/rand"

// A Synapse forms a connection between two Neurons
type Synapse struct {
	weight float64
	orig   *Neuron
	dest   *Neuron
}

// A Neuron performs some calculations to decide if a signal should be sent
type Neuron struct {
	id       int
	thresh   int
	parents  []*Synapse
	children []*Synapse
}

/* Add a parent to the neuron */
func (n *Neuron) addParent(p *Neuron, s *Synapse) *Synapse {
	if s == nil {
		s = &Synapse{rand.Float64(), p, n}
	}
	n.parents = append(n.parents, s)
	return s
}

/* Add a child to the neuron */
func (n *Neuron) addChild(c *Neuron, s *Synapse) *Synapse {
	if s == nil {
		s = &Synapse{rand.Float64(), n, c}
	}
	n.children = append(n.children, s)
	return s
}
