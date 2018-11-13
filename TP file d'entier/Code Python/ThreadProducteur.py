# coding: utf-8

from threading import Thread
import time
import random
                    
#______________Class ThreadProducteur____________________________________________________


class ThreadProducteur(Thread):

    def __init__(self, f, tempsSommeil, labProd, labFile, nom):
        Thread.__init__(self)
        self.tempsSommeil=tempsSommeil/1000
        self.f=f
        self.labFile=labFile
        self.labProd=labProd
        self.name=nom
        self.interrupted = False



    #*******_Methode_******
        
    def interrupt(self):
        self.interrupted = True

    def run(self):
        while not self.interrupted:
            e = random.randint(1,100)
            
            self.labProd.config(text=self.name+self.f.enfiler(e))
            self.labFile.config(text=self.f.toString())
            
            self.labProd.update()
            self.labFile.update()
            time.sleep(self.tempsSommeil)
