package com.java.bandwidth;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

public class BandwidthClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        // 서버 네트워크 대역폭 제한하기 (서버, 데이터 베이스)

        // 서버에서 큰 용량 (데이터 등)을 가져오거나 보낼 때 대역폭 제한이 필요하다.
        // 제한을 하지 않으면 네트워크에 과부화 문제가 발생하거나 IDC 과금 문제가 발생한다. (서버를 계속 늘려 돈만 많이 든다.)
        // 그래서 네트워크 대역폭을 제한하는 방법이 필요하다.

        // 가장 많이 사용되는 방법은 ethtool과 tc 설정

        // 1. 대역폭
        // 데이터를 전송하기 위한 통로
        // 전송되는 데이터를 허용할 수 있는 동시접속자 수와 같은 의미
        // (예. 고속도로, 4차선 고속도로보다 8차선 고속도로가 더욱 교통이 원활하다)
        // 초당 전송될 수 있는 최대량을 의미

        // ethtool과 tc 차이점
        // 1) ethtool
        // 장점 - 설정이 tc보다 쉽다.
        // 단점 - 트래픽 제한을 10^n으로만 설정가능 (10, 100, 1000...)

        // 2) tc
        // 장점 - 트래픽 제한을 10^n 설정이 아닌 세밀하게 설정가능
        // 단점 - ethtool보다 설정이 복잡

        // 네트워크 처리량 측정하기
        // 1) iperf3
        // 대역폭을 제한하기 전 iperf3를 설치하여 대역폭 속도가 제한되는지 테스트 각능
        // 서버에서 -s 옵션으로 서버 설정 -> 다른 서버에서 -c 옵션으로 클라이언트 설정
        // 서버는 트래픽을 처리하기 위한 용도, 클라이언트는 트래픽을 생성하기 위한 용도로 사용

        // 속도 테스트하기 (centos or ubuntu)
        // 서버 - iperf3 -s 입력
        // 클라이언트 - iperf3 -c <server용 서버1 ip>
        // 현재 속도 확인 - ethtool 네트워크 장치명

        // 네트워크 속도 원하는 속도로 변경 - ethtool
        // ethtool -s 네트워크 장치명 speed 변경할 속도 duplex full autoneg off

        // 2) tc
        // 네트워크 장치명 확인 - ifconfig
        // tc shell script 생성 - 네트워크 장치명_tc.sh
        // sh script 실행 - sh 네트워크 장치명_tc.sh start

	}

}
