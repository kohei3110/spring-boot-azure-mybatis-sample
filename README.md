# Spring Boot + MyBatis To Do App (Demo)

App Service + Azure SQL Database + Front Door を想定した、最小構成の To Do アプリです。**PaaS 最適化はあえて行わない**前提で、後から改善できる余地を見せるデモ用途として作っています。

## 目的
- To Do の CRUD を最小構成で動かす
- App Service / Azure SQL / Front Door を前提にした構成を説明できる状態にする
- 非最適化ポイントと改善余地を明示する

## 構成
- Spring Boot 3 / Java 17
- MyBatis (XML Mapper)
- Azure SQL Database (SQL Server)
- Flyway (DB マイグレーション)
- Thymeleaf (簡易 UI)

## ルーティング
- UI: `GET /` (一覧 + 入力フォーム)
- API:
  - `GET /api/todos`
  - `GET /api/todos/{id}`
  - `POST /api/todos`
  - `PUT /api/todos/{id}`
  - `DELETE /api/todos/{id}`

## DB スキーマ
- `todos` テーブル (Flyway: `V1__create_todos_table.sql`)

## 環境変数
Azure SQL に接続するため、以下を設定します。

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

`.env` に書く場合の例:
```
SPRING_DATASOURCE_URL=jdbc:sqlserver://<server>.database.windows.net:1433;databaseName=todo_db;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net
SPRING_DATASOURCE_USERNAME=<user>
SPRING_DATASOURCE_PASSWORD=<password>
```

## ローカル実行
1. Azure SQL Database に `todo_db` を用意
2. 環境変数を設定
3. 起動

## App Service / Front Door 前提の補足
- App Service でそのまま起動できる構成 (Spring Boot 実行)
- Front Door の背後に配置することを想定し、アプリ側では特別な最適化は行っていません

## 非最適化ポイント（意図的）
- DB 接続プールのチューニング未実施
- リトライ/バックオフの実装なし
- キャッシュ戦略なし
- 監視/トレース/ログの整備なし
- Health Check / Readiness / Liveness の整備なし
- Front Door によるヘッダー最適化や HTTPS 設定の最小化
- App Service のスケールアウト/ゾーン冗長の考慮なし

## 改善余地
- HikariCP のパラメータ調整、接続再利用の最適化
- Resilience4j などによるリトライ/サーキットブレーカー
- App Service のヘルスチェック対応
- Front Door の WAF、キャッシュ、ヘッダー制御
- Azure Monitor / App Insights 連携
- 分割アーキテクチャや API Gateway 化

## 動作確認
- CRUD の一通りを UI/API で確認
- Azure SQL Database への接続確認
