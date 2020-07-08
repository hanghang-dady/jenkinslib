package  org.devops

//集成saltstack

def  SaltDeploy(host,func){

    sh " salt \"${hosts}\" ${func}"
}
