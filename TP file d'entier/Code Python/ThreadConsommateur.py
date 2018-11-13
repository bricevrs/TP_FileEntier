# coding: utf-8

from threading import Thread
import time

#________________Class ThreadConsommateur________________________________________________


class ThreadConsommateur(Thread):

    def __init__(self, f, tempsSommeil, labConso, labFile, nom):
        Thread.__init__(self)
        self.tempsSommeil=tempsSommeil/1000
        self.f=f
        self.labFile=labFile
        self.labConso=labConso
        self.name=nom
        self.interrupted=False

    #******_Methode_*******

    def interrupt(self):
        self.interrupted = True    

    def run(self):
        while not self.interrupted:
            self.labConso.config(text=self.name+self.f.defiler())
            self.labFile.config(text=self.f.toString())
            self.labConso.update()
            self.labFile.update()
            time.sleep(self.tempsSommeil)
