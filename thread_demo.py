#Initial versio: Ver. 1.0
import threading

def runner(number):
    print('Runner No: ', number)
    return
threads=[]

for i in range(10):
    t= threading.Thread(target=runner, args=(i,))
    threads.append(t)
    t.start()
#    t.join()
