# Issue: App Service / SQL Database / Front Door 構成のシンプルTo Doアプリ（デモ用）

## 背景 / 目的
- App Service、SQL Database、Front Door を使って動作するシンプルな To Do アプリを作成する。
- ORM は MyBatis、フレームワークは Spring Boot を使用する。
- **PaaS最適化は意図的に行わない**実装とし、後でリファクタリング余地を示すデモ用途とする。

## 期待する成果
- 最小構成で To Do の CRUD が動作するアプリケーション一式。
- App Service / SQL Database / Front Door を前提とした構成で動作可能なことが説明できる状態。
- 意図的な非最適化ポイントが明示され、後続の改善デモに利用できる。

## 要件
### 機能要件
- To Do の作成 / 取得 / 更新 / 削除（CRUD）。
- シンプルな UI（一覧＋入力フォーム程度）。

### 技術要件
- Spring Boot + MyBatis。
- SQL Database に対して MyBatis でアクセス。
- App Service でホスティング可能な構成。
- Front Door 経由のアクセスを想定したエンドポイント/構成。

### 非機能要件（意図的に非最適化）
- PaaS らしい最適化（例: スケール設定、接続最適化、ヘルスチェック最適化など）を**あえて**行わない。
- その理由と改善余地を README などで説明可能にする。

## スコープ外
- 高度な認証/認可。
- マルチテナント対応。
- 高可用性/自動スケール設定の最適化。
- 監視/ロギングの高度化。

## 受け入れ条件（Definition of Done）
- To Do の CRUD が動作する。
- Spring Boot + MyBatis + SQL Database の接続が確認できる。
- App Service / Front Door を前提とした構成説明がある。
- 非最適化ポイントと改善余地が明示されている。

## 実装タスク（案）
- [ ] Spring Boot プロジェクトの初期構成
- [ ] MyBatis 設定（SQL Database 接続）
- [ ] To Do テーブル設計 & マイグレーション準備
- [ ] CRUD API 実装
- [ ] 簡易 UI 実装
- [ ] README に構成図/前提/非最適化ポイント記載

## メモ
- デモ用途のため「ベストプラクティス準拠」よりも「後で改善できる状態の可視化」を優先する。
