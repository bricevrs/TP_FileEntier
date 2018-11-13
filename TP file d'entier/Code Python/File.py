# coding: utf-8

from threading import Condition

#________________Class File______________________________________________________________

class File :
    def __init__(self):
        self.tabFile=[]
        self.cond=Condition()

    
    #******_Methode_*****

    def toString(self):
        with self.cond:
            chaineFile = "|File| = " + str(len(self.tabFile))+" :   <"
            for i,e in enumerate(self.tabFile):
                if(i==len(self.tabFile)-1):
                    chaineFile+=str(self.tabFile[i])
                else:
                    chaineFile+=str(self.tabFile[i])+", "
            chaineFile+="<"
            return chaineFile

            

    def enfiler(self, e):
        with self.cond:
            while(len(self.tabFile)==20):
                self.cond.wait()
            self.tabFile.append(e)
            self.cond.notifyAll()
            return (" produit "+str(e)+"\n")

        

    def defiler(self):
        with self.cond:
            while(len(self.tabFile)==0):
                  self.cond.wait()
            e = self.tabFile[0]
            del self.tabFile[0]
            self.cond.notifyAll()
            return (" consomme "+str(e))

    def clear(self):
        with self.cond:
            self.tabFile.clear
