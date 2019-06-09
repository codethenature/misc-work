#!/usr/bin/python

def calc_grade(data, maxhash, key):
    a1 = (float(data[key]['a1'])/float(maxhash['a1']))*7.5 if data[key].has_key('a1') else 0
    a2 = (float(data[key]['a2'])/float(maxhash['a2']))*7.5 if data[key].has_key('a2') else 0
    pr = (float(data[key]['pr'])/float(maxhash['pr']))*25 if data[key].has_key('pr') else 0
    t1 = (float(data[key]['t1'])/float(maxhash['t1']))*30 if data[key].has_key('t1') else 0
    t2 = (float(data[key]['t2'])/float(maxhash['t2']))*30 if data[key].has_key('t2') else 0
    return a1+a2+pr+t1+t2

def calc_fl(val, xx):
    x = (100 - xx)/7;
    if ( val > 99-x ): return "A+"
    elif ( val > 99-2*x ): return "A"
    elif ( val > 99-3*x ): return "A-"
    elif ( val > 99-4*x ): return "B+"
    elif ( val > 99-5*x ): return "B"
    elif ( val > 99-6*x ): return "B-"
    elif ( val > xx ): return "C"
    else: return "F"

def func1(data, maxhash, sub):
    "subject wise marks printing"
    sub = sub.lower()
    if ( not maxhash.has_key( sub ) ) :
        return 0
    print sub.upper()," grades (",maxhash[sub],")"
    for key in sorted(data):
        if ( data[key].has_key(sub) ) :
            print key,data[key]['ln'],data[key]['fn'],data[key][sub]
    return 1

def func2(data, maxhash, sub):
    "subject average"
    sub = sub.lower()
    if ( not maxhash.has_key( sub ) ) :
        return 0
    count = 0
    sum = 0
    for key in data:
        if ( data[key].has_key(sub) ) :
            count+=1
            sum+= int(data[key][sub])
    print sub.upper(),"average:",sum/float(count),"/",maxhash[sub]
    return 1



def func3(data, maxhash, x):
    if ( not (x>=0 and x<=93) ) :
        return 0

    print "{0:8s}{1:8s}{2:8s}{3:8s}{4:8s}{5:8s}{6:8s}{7:8s}{8:8s}{9:8s}".format("ID", "LN","FN", "A1","A2", "PR","T1", "T2","GR", "FL")
    for key in sorted(data):
        print "{0:8s}{1:8s}{2:8s}{3:8s}{4:8s}{5:8s}{6:8s}{7:8s}{8:<8.2f}{9:8s}".format(
            key, 
            data[key]['ln'],
            data[key]['fn'],
            data[key]['a1'] if data[key].has_key('a1') else '0',
            data[key]['a2'] if data[key].has_key('a2') else '0',
            data[key]['pr'] if data[key].has_key('pr') else '0',
            data[key]['t1'] if data[key].has_key('t1') else '0',
            data[key]['t2'] if data[key].has_key('t2') else '0',
            data[key]['gr'],
            calc_fl(data[key]['gr'],x)
            )
    return 1

def func4(data, maxhash, order_col, x ):
    order_col = order_col.lower()
    if ( not (order_col == 'ln' or order_col == 'gr') ) :
        return 0
    print "{0:8s}{1:8s}{2:8s}{3:8s}{4:8s}{5:8s}{6:8s}{7:8s}{8:8s}{9:8s}".format("ID", "LN","FN", "A1","A2", "PR","T1", "T2","GR", "FL")


    for row in sorted(data.items(), key=lambda x:x[1][order_col]):
        print "{0:8s}{1:8s}{2:8s}{3:8s}{4:8s}{5:8s}{6:8s}{7:8s}{8:<8.2f}{9:8s}".format(
            row[0], 
            row[1]['ln'],
            row[1]['fn'],
            row[1]['a1'] if row[1].has_key('a1') else '0',
            row[1]['a2'] if row[1].has_key('a2') else '0',
            row[1]['pr'] if row[1].has_key('pr') else '0',
            row[1]['t1'] if row[1].has_key('t1') else '0',
            row[1]['t2'] if row[1].has_key('t2') else '0',
            row[1]['gr'],
            calc_fl(row[1]['gr'],x)
            )
    return 1

