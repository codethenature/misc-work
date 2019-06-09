#!/usr/bin/python

import compute

def show():
    print 
    print "---------------------------------"
    print "1> Display individual component"
    print "2> Display component average"
    print "3> Display Standard Report"
    print "4> Sort by alternate column"
    print "5> Change Pass/Fail point"
    print "6> Exit"
    print
    print ">> Press any key between 1-6 !"
    print "---------------------------------"

def read():
    
	classfile='./class.txt'
	a1file='./a1.txt'
	a2file='./a2.txt'
	prfile='./project.txt'
	t1file='./test1.txt'
	t2file='./test2.txt'

        global data
	global maxhash
	data = {}
	maxhash = {}

	with open(classfile) as file:
	    for line in file:
		line = line.replace(' ', '').strip()
		sid, fn, ln = line.split('|')
		data[sid] = {}
		data[sid]['fn'] = fn
		data[sid]['ln'] = ln


	with open(a1file) as file:
	    maxhash['a1'] = file.readline().strip()
	    for line in file:
		line = line.replace(' ', '').strip()
		sid, marks = line.split('|')
		data[sid]['a1'] = marks

	with open(a2file) as file:
	    maxhash['a2'] = file.readline().strip()
	    for line in file:
		line = line.replace(' ', '').strip()
		sid, marks = line.split('|')
		data[sid]['a2'] = marks

	with open(prfile) as file:
	    maxhash['pr'] = file.readline().strip()
	    for line in file:
		line = line.replace(' ', '').strip()
		sid, marks = line.split('|')
		data[sid]['pr'] = marks

	with open(t1file) as file:
	    maxhash['t1'] = file.readline().strip()
	    for line in file:
		line = line.replace(' ', '').strip()
		sid, marks = line.split('|')
		data[sid]['t1'] = marks

	with open(t2file) as file:
	    maxhash['t2'] = file.readline().strip()
	    for line in file:
		line = line.replace(' ', '').strip()
		sid, marks = line.split('|')
		data[sid]['t2'] = marks

	for key in data:
            data[key]['gr'] = compute.calc_grade(data, maxhash, key)

def _main_():
    
    read()

    while 1:
        show()
        inp = input()
        if inp == 1 :
            sub = raw_input("Component Name: ")
            res = compute.func1(data, maxhash, sub)
        elif inp == 2 :
            sub = raw_input("Component Name: ")
            res = compute.func2(data, maxhash, sub)
        elif inp == 3:
            res = compute.func3(data, maxhash, 50)
        elif inp == 4:
            col = raw_input("Alternate Column: LN/GR ")
            res = compute.func4(data, maxhash, col, 50)
        elif inp == 5:
            fpoint = input("Pass/Fail Point: ")
            res = compute.func3(data, maxhash, fpoint)
        else:
            print "Good Bye."
            break

        if res == 0 : print "Error occurred in input, try again!"

_main_()
