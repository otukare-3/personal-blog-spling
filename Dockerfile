# ベースイメージとしてOpenJDK 17を使用
FROM openjdk:17-jdk-slim

# Gradleのバージョンを指定
ARG GRADLE_VERSION=7.5.1

# 必要なパッケージをインストールし、Gradleをダウンロードしてインストール
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -P /tmp && \
    unzip /tmp/gradle-${GRADLE_VERSION}-bin.zip -d /opt && \
    ln -s /opt/gradle-${GRADLE_VERSION} /opt/gradle && \
    rm /tmp/gradle-${GRADLE_VERSION}-bin.zip && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Gradleのパスを設定
ENV GRADLE_HOME=/opt/gradle
ENV PATH=${GRADLE_HOME}/bin:${PATH}

# 作業ディレクトリを設定
WORKDIR /app

# 現在のプロジェクトを作業ディレクトリにコピー
COPY . .

# コンテナ起動時のデフォルトコマンド
CMD ["./gradlew", "bootRun"]