#!groovy
//Jenkins share Library  共享库

@Library('jenkinslib@master') _

def tools = new org.devops.tools()


String workspace = "/var/jenkins_home/workspace"

pipeline {
    agent { node { label "master"  //指定节点的标签或者名称 
                   customWorkspace "${workspace}"   //指定运行工作目录（可选）
            }
    }

    options {
        timestamps()   //日志会有时间
        skipDefaultCheckout()   //在代理指令中，跳过从源代码控制中检出代码。例如： options { skipDefaultCheckout() }
        disableConcurrentBuilds()  //禁止并行
        timeout(time: 2, unit：'MINUTES') //设置流水线超时时间2分钟
    }

    stages {
        //拉去代码
        stage('拉取代码'){  //阶段名称
            when { environment name: 'test', value: 'abcd'}
            steps{  //步骤
                timeout(time:5, unit:"MINUTES"){
                    script{  //填写运行代码
                        println('获取代码')
                        println("${test}")
    
                        input id: 'Test', message: '是否继续构建？', ok: '是，继续吧!', parameters: [choice(choices: ['a', 'b'], description: '', name: 'test1')], submitter: 'admin'
                    }
                }
            }
        }

        stage("01"){
            failFast true
            parallel {

                //代码构建
                stage('代码构建'){
                    steps{
                        timeout(time:20, unit:"MINUTES"){
                            script{
                                println('应用打包')

                                mvnHome = tool "m2"
                                println(mvnHome)

                                sh "${mvnHome}/bin/mvn --version"
                            }
                        }
                    }
                }

                //代码扫描
                stage('代码扫描'){
                    steps{
                        timeout(time:30, unit: "MINUTES"){
                            script{
                                println('代码扫描')

                                tools.PrintMes("this is my lib~")  //这个tools就是我们共享库里面定义的 tools文件里面内容
                            }
                        }
                    }
                }
            }
        }
    }

    //构建后操作
    post {
        always {
            script{
                println("always")
            }
        }

        success {
            script{
                currentBuild.description = "\n 构建成功"
            }
        }

        failure {
            script{
                currentBuild.description = "\n 构建失败"
            }
        }

        aborted {
            script{
                currentBuild.description = "\n 构建终止"
            }
        }
    }
}
