package  org.devops

//集成saltstack

def  SaltDeploy(hosts,func){

    sh " salt \"${hosts}\" ${func}"
}
