# Isolated Ruflo runtime smoke test

## Objective
Verify the public `ruflo@3.80.0` CLI wrapper and `@claude-flow/cli@3.80.0` can load in a GitHub-hosted, disposable Linux runner. Source repository is pinned to `ruvnet/ruflo@87f612cef121b50c2e31e2d3f88e46d88963a377` in the central skills library, but the npm release must be independently verified as packaged code; matching version numbers do not prove matching Git source.

## Safety boundary
- GitHub Actions `contents: read`, no explicit model, database, cloud or personal secrets, no persistent credentials.
- `npm install --ignore-scripts --prefix "$RUNNER_TEMP/ruflo-smoke" ruflo@3.80.0`: nothing is installed globally or into any production project and package lifecycle scripts do not run.
- Checks: Node/npm version; exact Ruflo package versions; CLI `--version` and `--help` within time limits; no tracked repository diff.
- No `ruflo init`, `doctor --fix`, `swarm init`, MCP server, browser automation, offline data, migrations or deployment.

## How to interpret results
A passing smoke test proves the wrapper can start and display help on the selected Linux runner in this constrained install mode. It **does not** establish Windows, Android, iOS, production, network, workload throughput or full 403-skill compatibility. Any failure should be triaged from the actual log and not papered over by installing arbitrary `@latest` dependencies.

Windows device `DESKTOP-KJBB96J` must be verified online and selected explicitly before testing there. Do not silently substitute another connected device.
