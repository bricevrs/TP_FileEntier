# coding: utf-8

from tkinter import *
from File import *
from ThreadProducteur import *
from ThreadConsommateur import *


#________________Class Fenetre___________________________________________________________


class Fenetre(Frame):

    def __init__(self, fenetre, timeProd, timeConsos):
        Frame.__init__(self)
        self.pack(fill=BOTH)
        

        #Attributs time

        self.timeProd = timeProd
        self.timeConsos = timeConsos

        #Creation de la file

        self.f = File()

        #Creation des JLabels/JButtons

        self.labFile = Label(self,text="<  <",font=("Courrier New",20))
        self.labProd = Label(self,text="", fg="blue",font=("Courrier New",20))
        self.labConso = Label(self,text="", fg="red",font=("Courrier New",20))

        self.stopAndStart = Button(self, text="Start", command = self.cliquer,font=("Courrier New",20))
        self.stopAndStart.pack(side="bottom", fill=BOTH)
        self.labProd.pack(side="bottom",fill=BOTH)
        self.labConso.pack(side="bottom",fill=BOTH)
        self.labFile.pack(side="bottom",fill=BOTH)


    #******_Methode_*******

    #___________Creation Thread_________

    def createThread(self):
        self.prod = ThreadProducteur(self.f, self.timeProd, self.labProd, self.labFile, "Producteur")
        self.consos = []
        for i,time in enumerate(self.timeConsos):
            self.consos.append(ThreadConsommateur(self.f, time, self.labConso, self.labFile, "Consommateur"+str((i+1))))


    #___________Start Thread____________

    def startThread(self):
        self.createThread()
        self.prod.start()
        for threadConso in self.consos:
            threadConso.start()


    #___________Interrupt Thread_____________

    def stopThread(self):
        self.prod.interrupt()
        for threadConso in self.consos:
            threadConso.interrupt()
        #self.f.clear()
        #self.labFile.config(text="< <")
        self.labConso.config(text="")
        self.labProd.config(text="")
        
        self.labFile.update()
        self.labConso.update()
        self.labProd.update()
        

    #________Traitement Bouton________
    def cliquer(self):
        if(self.stopAndStart.cget("text")=="Start"):
            self.startThread()
            self.stopAndStart.config(text="Stop")

        elif(self.stopAndStart.cget("text")=="Stop"):
            self.stopThread()
            self.stopAndStart.config(text="Start")

#_______________________________Execution _______________________________________________

if __name__ =="__main__":
    #Initialisation du generateur de nombres aléatoires
    random.seed()

    timeProd = 250
    timeConsos = [1000, 2000,3000]

    fenetre = Tk()
    fenetre.geometry("1000x600")
    f1 = Fenetre(fenetre, timeProd, timeConsos)
    f1.mainloop()




