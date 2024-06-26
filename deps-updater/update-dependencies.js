const fs = require('fs');
const { execSync } = require('child_process');

execSync('cd .. && sbt dependencyUpdatesReport');

const reportFile = '../service/target/dependency-updates.txt';

if (!fs.existsSync(reportFile)) {
  console.error('Dependency updates report file not found.');
  process.exit(1);
}

const report = fs.readFileSync(reportFile, 'utf8');
const updateLines = report.split('\n').filter(line => line.includes(' -> '));

let buildSbtContent = fs.readFileSync('../build.sbt', 'utf8');
console.log(report)
updateLines.forEach(line => {
  if(!line.indexOf("alpha") >-1) {
    const [currentDependency, newVersion] = line.split(' -> ').map(s => s.trim());
    const [organization, artifact, oldVersion] = currentDependency.split(':').map(s => s.trim());
    console.log(`Found organization: ${organization}, artifact: ${artifact}, old version: ${oldVersion}, new possible version ${newVersion}`);
    let numericNewVersion = Number(newVersion.split(".").map(seg => seg.padStart(4,'0')).join(''));
    let numericOldVersion = Number(oldVersion.split(".").map(seg => seg.padStart(4,'0')).join(''));
    console.log(`Parsed new version is ${numericNewVersion} and parsed old version is ${numericOldVersion}`);
    if(numericNewVersion > numericOldVersion) {
      const regex = new RegExp(`("${organization}" %% "${artifact}" % ").*(",?)`);
      buildSbtContent = buildSbtContent.replace(regex, `$1${newVersion}$2`);
    }
  }
});
fs.writeFileSync('../build.sbt', buildSbtContent);
